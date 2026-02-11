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
let gameOver = false;

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

const mobs = [];

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
  mobs.length = 0;
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

function getMobAt(x, y) {
  return mobs.find(m => m.x === x && m.y === y);
}

canvas.addEventListener('contextmenu', (e) => e.preventDefault());
canvas.addEventListener('mousedown', (e) => {
  const rect = canvas.getBoundingClientRect();
  const tx = Math.floor((e.clientX - rect.left) / TILE);
  const ty = Math.floor((e.clientY - rect.top) / TILE);
  if (tx < 0 || ty < 0 || tx >= W || ty >= H) return;
  if (Math.abs(tx - player.x) + Math.abs(ty - player.y) > 1) return;

  const mob = getMobAt(tx, ty);
  if (mob && e.button === 0) {
    mob.hp -= 6;
    if (mob.hp <= 0) {
      mobs.splice(mobs.indexOf(mob), 1);
      const dirtSlot = player.hotbar.find(s => s.id === 'dirt');
      if (dirtSlot) dirtSlot.count += 1;
    }
    return;
  }

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
    if (getMobAt(tx, ty)) return;
    const slot = player.hotbar[player.hotbarIndex];
    if (!slot || slot.count <= 0) return;
    world[ty][tx] = slot.id;
    slot.count--;
  }
});

function spawnMob() {
  if (mobs.length > 12) return;
  const day = ((Math.floor(tick / 120) % 2) === 0);
  const allow = !day || dimension !== 'overworld';
  if (!allow || Math.random() > 0.18) return;

  const px = player.x + (Math.random() < 0.5 ? -1 : 1) * (4 + Math.floor(Math.random() * 8));
  const py = player.y + (Math.random() < 0.5 ? -1 : 1) * (3 + Math.floor(Math.random() * 6));
  if (px < 1 || py < 1 || px >= W - 1 || py >= H - 1) return;
  if (world[py][px] !== 'air') return;
  if (getMobAt(px, py)) return;

  const type = dimension === 'end' ? 'enderman' : (dimension === 'nether' ? 'ghast' : 'zombie');
  mobs.push({ x: px, y: py, hp: type === 'enderman' ? 14 : 10, type });
}

function updateMobs() {
  for (const m of mobs) {
    const dx = Math.sign(player.x - m.x);
    const dy = Math.sign(player.y - m.y);
    const nx = m.x + (Math.random() < 0.6 ? dx : 0);
    const ny = m.y + (Math.random() < 0.6 ? dy : 0);

    if (Math.abs(player.x - m.x) + Math.abs(player.y - m.y) <= 1) {
      if (tick % 20 === 0) player.hp = Math.max(0, player.hp - (m.type === 'enderman' ? 3 : 2));
      continue;
    }

    if (nx >= 1 && ny >= 1 && nx < W - 1 && ny < H - 1 && world[ny][nx] === 'air' && !getMobAt(nx, ny)) {
      m.x = nx;
      m.y = ny;
    }
  }
}

function step() {
  if (gameOver) return;
  tick++;
  let nx = player.x;
  let ny = player.y;
  if (keys.has('w')) ny--;
  if (keys.has('s')) ny++;
  if (keys.has('a')) nx--;
  if (keys.has('d')) nx++;
  if (nx >= 1 && ny >= 1 && nx < W - 1 && ny < H - 1 && world[ny][nx] === 'air' && !getMobAt(nx, ny)) {
    player.x = nx; player.y = ny;
  }

  if (tick % 30 === 0 && dimension === 'overworld') {
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

  if (tick % 15 === 0) spawnMob();
  if (tick % 8 === 0) updateMobs();
  if (player.hp <= 0) gameOver = true;
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

  for (const m of mobs) {
    ctx.fillStyle = m.type === 'enderman' ? '#6a4c93' : (m.type === 'ghast' ? '#f1f1f1' : '#d94f4f');
    ctx.fillRect(m.x * TILE + 3, m.y * TILE + 3, TILE - 6, TILE - 6);
  }

  ctx.fillStyle = '#ffd166';
  ctx.fillRect(player.x * TILE + 4, player.y * TILE + 4, TILE - 8, TILE - 8);

  if (gameOver) {
    ctx.fillStyle = 'rgba(0,0,0,.65)';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = '#ff6b6b';
    ctx.font = 'bold 42px Arial';
    ctx.fillText('You Died', canvas.width / 2 - 95, canvas.height / 2);
  }

  stats.textContent = `Dim: ${dimension} | HP: ${player.hp} | Tick: ${tick} | Mobs: ${mobs.length} | Cycle: ${day ? 'day' : 'night'}`;
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
