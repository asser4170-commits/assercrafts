const canvas = document.getElementById('game');
const ctx = canvas.getContext('2d');
const stats = document.getElementById('stats');
const hotbarEl = document.getElementById('hotbar');

const TILE = 24;
const W = 40;
const H = 26;
const world = [];
let tick = 0;
let dimension = 'overworld';

const blocks = {
  air: { c: 'rgba(0,0,0,0)' },
  grass: { c: '#5fa34a' },
  dirt: { c: '#7b5130' },
  stone: { c: '#80858c' },
  sand: { c: '#d8c37f' },
  water: { c: '#3a78d3' },
  netherrack: { c: '#7f3a3a' },
  soul_sand: { c: '#5a412f' },
  end_stone: { c: '#d6d3ad' },
  obsidian: { c: '#3d2f59' },
  torch: { c: '#f5c64b' }
};

const player = {
  x: Math.floor(W / 2),
  y: Math.floor(H / 2),
  hp: 20,
  hotbarIndex: 0,
  hotbar: [
    { id: 'grass', count: 32 },
    { id: 'stone', count: 32 },
    { id: 'sand', count: 32 },
    { id: 'torch', count: 32 },
    { id: 'obsidian', count: 16 },
    { id: 'netherrack', count: 16 },
    { id: 'soul_sand', count: 16 },
    { id: 'end_stone', count: 16 },
    { id: 'dirt', count: 32 }
  ]
};

function generateWorld(dim) {
  for (let y = 0; y < H; y++) {
    world[y] = [];
    for (let x = 0; x < W; x++) {
      const edge = x < 1 || y < 1 || x > W - 2 || y > H - 2;
      if (edge) { world[y][x] = 'obsidian'; continue; }
      if (dim === 'overworld') {
        if (Math.random() < 0.08) world[y][x] = 'water';
        else if (Math.random() < 0.15) world[y][x] = 'stone';
        else world[y][x] = Math.random() < 0.6 ? 'grass' : 'dirt';
      } else if (dim === 'nether') {
        world[y][x] = Math.random() < 0.2 ? 'soul_sand' : 'netherrack';
      } else {
        world[y][x] = Math.random() < 0.1 ? 'obsidian' : 'end_stone';
      }
    }
  }
  world[player.y][player.x] = 'air';
}

generateWorld(dimension);

const keys = new Set();
window.addEventListener('keydown', (e) => {
  keys.add(e.key.toLowerCase());
  if (e.key >= '1' && e.key <= '9') player.hotbarIndex = Number(e.key) - 1;
  if (e.key.toLowerCase() === 'f') {
    dimension = dimension === 'overworld' ? 'nether' : (dimension === 'nether' ? 'end' : 'overworld');
    generateWorld(dimension);
  }
});
window.addEventListener('keyup', (e) => keys.delete(e.key.toLowerCase()));

canvas.addEventListener('contextmenu', (e) => e.preventDefault());
canvas.addEventListener('mousedown', (e) => {
  const rect = canvas.getBoundingClientRect();
  const tx = Math.floor((e.clientX - rect.left) / TILE);
  const ty = Math.floor((e.clientY - rect.top) / TILE);
  if (tx < 0 || ty < 0 || tx >= W || ty >= H) return;
  if (Math.abs(tx - player.x) + Math.abs(ty - player.y) > 1) return;

  if (e.button === 0) {
    const b = world[ty][tx];
    if (b !== 'air' && b !== 'obsidian') {
      world[ty][tx] = 'air';
      const slot = player.hotbar.find(s => s.id === b);
      if (slot) slot.count++;
    }
  }
  if (e.button === 2) {
    if (world[ty][tx] !== 'air') return;
    const slot = player.hotbar[player.hotbarIndex];
    if (!slot || slot.count <= 0) return;
    world[ty][tx] = slot.id;
    slot.count--;
  }
});

function step() {
  tick++;
  let nx = player.x;
  let ny = player.y;
  if (keys.has('w')) ny--;
  if (keys.has('s')) ny++;
  if (keys.has('a')) nx--;
  if (keys.has('d')) nx++;
  if (nx >= 1 && ny >= 1 && nx < W - 1 && ny < H - 1 && world[ny][nx] === 'air') {
    player.x = nx; player.y = ny;
  }

  if (tick % 30 === 0) {
    // simple fluid spread in overworld
    if (dimension === 'overworld') {
      for (let y = H - 2; y >= 1; y--) {
        for (let x = 1; x < W - 1; x++) {
          if (world[y][x] === 'water') {
            if (world[y + 1][x] === 'air') world[y + 1][x] = 'water';
            else if (world[y][x + 1] === 'air') world[y][x + 1] = 'water';
            else if (world[y][x - 1] === 'air') world[y][x - 1] = 'water';
          }
        }
      }
    }
  }
}

function draw() {
  const day = ((Math.floor(tick / 120) % 2) === 0);
  ctx.fillStyle = day ? '#7ec7ff' : '#0f1b2d';
  ctx.fillRect(0, 0, canvas.width, canvas.height);

  for (let y = 0; y < H; y++) {
    for (let x = 0; x < W; x++) {
      const b = world[y][x];
      if (b === 'air') continue;
      ctx.fillStyle = blocks[b]?.c || '#fff';
      ctx.fillRect(x * TILE, y * TILE, TILE - 1, TILE - 1);
    }
  }

  ctx.fillStyle = '#ffd166';
  ctx.fillRect(player.x * TILE + 4, player.y * TILE + 4, TILE - 8, TILE - 8);

  stats.textContent = `Dim: ${dimension} | HP: ${player.hp} | Tick: ${tick} | Cycle: ${day ? 'day' : 'night'}`;
  hotbarEl.innerHTML = '';
  player.hotbar.forEach((slot, i) => {
    const div = document.createElement('div');
    div.className = 'slot' + (i === player.hotbarIndex ? ' active' : '');
    div.textContent = `${i + 1}: ${slot.id} x${slot.count}`;
    hotbarEl.appendChild(div);
  });
}

function loop() {
  step();
  draw();
  requestAnimationFrame(loop);
}
loop();
