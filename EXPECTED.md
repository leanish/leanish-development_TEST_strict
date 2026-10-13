# Expected behavior

A strict version (`strictly(...)`, declared or as a dependency constraint) is the owner's pin: the tools never move
it, never plan a change that needs it moved, and say so.

| Case | Build | bump-it | secure-it |
|---|---|---|---|
| 1 | `commons-lang3` 3.12.0, plain | Moves it (routine). | — |
| 2 | `commons-io` strictly 2.11.0 | Leaves it; notes the pin. | — |
| 3 | `guava` 32.0.0-jre plain in `implementation`, strict in `testImplementation` | Leaves both declarations; notes the pin. | — |
| 4 | `jackson-databind` 2.15.2 plain, plus a strict constraint at 2.15.2 | Leaves it; notes the pin. | Blocked if it has advisories. |
| 5 | `snakeyaml` strictly 1.33 (vulnerable) | Leaves it; notes the pin. | Reports the fix as blocked by the pin; the gate keeps failing until the owner decides. |
| 6 | `commons-text` 1.9, plain (vulnerable) | Moves it (routine). | Fixes it. |
| 7 | `okhttp` strictly 4.9.3, which brings a vulnerable `okio` | Leaves `okhttp`; notes the pin. | Never moves `okhttp` to carry the fix; floors `okio` instead. |
| 8 | `commons-compress` strictly 1.26.0, a recorded security floor | Leaves it (bump-it never changes floors). | Never removes it, even when the floor is redundant: retained with the pin as the reason. |
| 9 | `joda-time` 2.12.5 plain, with an inactive `strictly("2.12.5")` branch (`fixture.strictJoda`) | Moves the active declaration only; the inactive strict line stays as it is. | — |

Verification also refuses any edit that changes a line mentioning `strictly`.
