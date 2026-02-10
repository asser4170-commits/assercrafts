# Project Review Notes

Date: current branch audit

## Branch review

- Local branches discovered: `work` only.
- No additional local/remote feature branches were present to merge.

## Findings and fixes applied

1. **Data parsing robustness**
   - Added strict field-count validation in `DataLoader` so malformed data rows fail fast with clear error messages.

2. **1.0-era mob drop consistency**
   - Updated sheep drop table to remove `mutton` (not part of Java 1.0 era), keeping wool drop.

3. **Run documentation accuracy**
   - Added Maven `exec-maven-plugin` declaration and README fallback run path (`javac` + `java`) for constrained environments.

## Remaining limitations

- Current implementation is a headless simulation scaffold (non-OpenGL / non-interactive UI placeholders).
- Maven dependency/plugin resolution can fail in restricted networks; fallback manual compilation path is documented.
