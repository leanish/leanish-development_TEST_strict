# Validation

Real runs of secure-it and bump-it from leanish/supply-chain's `strict-pins-planners` branch (before release), with
Sol at medium effort, on 2026-10-13. Cases refer to [EXPECTED.md](EXPECTED.md).

## First run (pins counted repository-wide)

- secure-it: [#1](https://github.com/leanish/supply-chain_TEST_strict/pull/1) (commons-text, commons-lang3, and a
  jackson-core floor because its parent jackson-databind is pinned), [#2](https://github.com/leanish/supply-chain_TEST_strict/pull/2)
  (an okio 3.4.0 floor; okhttp stays at its strict 4.9.3). Blocked: snakeyaml, commons-io, guava, jackson-databind.
  The commons-compress floor was retained without a removal probe. Cost $0.32.
- bump-it: [#3](https://github.com/leanish/supply-chain_TEST_strict/pull/3) moved commons-lang3, commons-text, the
  active joda-time declaration and the Gradle wrapper; the inactive `strictly("2.12.5")` line is unchanged. Each
  strict pin is in the run's notes. Cost $0.08.
- Finding: kotlin-stdlib 1.4.10 (brought by okhttp) was blocked because Gradle pins its embedded Kotlin strictly on
  the buildscript and settings classpaths, and bump-it noted those Kotlin pins too.

## Second run (pins counted only where a change acts)

- secure-it updated #1 with a kotlin-stdlib 1.6.0 floor (okhttp, its parent, is strict); the other results are
  unchanged. Cost $0.12.
- bump-it: #3 already open; no Kotlin notes.

No PR changes a line mentioning `strictly`. CI (build and the supply-chain gate v0.4.2) passed on #1, #2 and #3;
`main` fails the gate as intended (the blocked advisories stay until the owner moves the pins).
