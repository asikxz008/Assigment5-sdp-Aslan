# Assignment 5 | Strategy Pattern

**Student:** Sabyr Aslan | **Group:** SE-2528 | **Option B:** Calculator  
**Repository:** https://github.com/asikxz008/Assigment5-sdp-Aslan

A minimal Java 17 example: one `Calculator` delegates to `CalculationStrategy`.
`Main` selects addition, subtraction and multiplication on the same calculator.
The context has no strategy-type conditions and knows no concrete strategy.
Only JDK classes are required.

## Build and run

Run from the repository root with JDK 17 or later:

```sh
javac --release 17 -encoding UTF-8 -Xlint:all -Werror -d out "@sources.txt"
java -cp out Main
```

Expected output (also recorded in `demo-output.txt`):

```text
Add: 12 + 4 = 16.0
Subtract: 12 - 4 = 8.0
Multiply: 12 * 4 = 48.0
```

In IntelliJ IDEA, open the repository, choose JDK 17, mark `src` as Sources Root,
and run `Main`. No program arguments or external dependencies are needed.

## Structure

| Role | Class | Responsibility |
|---|---|---|
| Strategy | CalculationStrategy | Declares `calculate(left, right)` |
| Concrete strategies | AddStrategy, SubtractStrategy, MultiplyStrategy | Each performs one arithmetic operation |
| Context | Calculator | Holds one strategy, delegates, supports `setStrategy` |
| Client | Main | Creates strategies and demonstrates runtime switching |

`src/Main.java` is the client; domain classes are in `src/com/aitu/sdp/calculator`.
Six application files are sufficient. Each strategy has one method; the context
has one constructor and two methods. Arithmetic uses standard Java `double`
semantics, including rounding and non-finite values.
Null strategies are rejected without replacing the previous valid strategy.

## Add a strategy

1. Add a class implementing `CalculationStrategy`; implement its one `calculate` method.
2. Add its source path to `sources.txt` and select it in the client with
   `calculator.setStrategy(new YourStrategy())`.
3. Recompile and run. Leave `Calculator`, the interface and existing strategies unchanged.

Commit `bfb73d7` introduced the context and two strategies. Commit `c5eb508`
added `MultiplyStrategy` and updated the client and source list.
`extension.diff` shows that no existing domain class changed.

## Report and verification

`report.md` is the required Moodle report. Its UML image loads from this public
repository, so the report can be uploaded separately; internet access is needed
to display the image. It also includes the complete PlantUML source.
`report.pdf` is a visual copy. `docs/class-diagram.puml`, `.svg` and `.png` contain
the UML, based on the Refactoring.Guru resource in the assignment and rendered
locally with PlantUML.

The optional verification harness is outside the application sources:

```sh
javac --release 17 -encoding UTF-8 -Xlint:all -Werror -cp out -d out docs/StrategyVerification.java
java -cp out StrategyVerification
```

Expected summary: `SUMMARY: 15/15 PASS`. Checks cover algorithms, negative,
zero and fractional inputs, operand order, runtime switching on the same object,
null rejection and an independent implementation. Actual Java 17.0.16 output is
in `docs/verification-output.txt`. Russian preparation notes: `defense-notes.txt`.
