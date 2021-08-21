
# Waters Towers

## Problem Definition

There is a landscape with hills and pits which has similar square shapes.

Max number of positions is `32000`. Height is between `0` and `32000`.

**For example**: The first position has height `2`, the second position has height `5`.

The landscape can be represented as a collection of heights:

`[2, 5, 6, 8, 1, 4, 6, 2, 7, 2, 8, 4, 2, 6, 8, 4, 2, 5, 6, 2, 3, 5, 0, 2, 4, 1, 5, 1, 4, 4, 6, 2, 5, 2, 5, 2, 4, 2, 5, 7, 2, 2, 4, 6, 9, 6, 4, 6, 3, 6, 3, 6, 7, 5, 3, 2]`


<pre>

                                                                                        X
      X             X       X                                                           X
      X         X   X       X                                                 X         X               X
    X X     X   X   X     X X       X                       X                 X       X X X   X   X   X X
  X X X     X   X   X     X X     X X     X         X       X   X   X       X X       X X X   X   X   X X X
  X X X   X X   X   X X   X X X   X X     X     X   X   X X X   X   X   X   X X     X X X X X X   X   X X X
  X X X   X X   X   X X   X X X   X X   X X     X   X   X X X   X   X   X   X X     X X X X X X X X X X X X X
X X X X   X X X X X X X X X X X X X X X X X   X X   X   X X X X X X X X X X X X X X X X X X X X X X X X X X X X
X X X X X X X X X X X X X X X X X X X X X X   X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X

</pre>
 
When it rains, the landscape is filled with water.

Water is collected inside pits only between hills.

**For example**: collected `183` squares of water

<pre>

      X O O O O O O X O O O X O O O O O O O O O O O O O O O O O O O O O O O O O O O O O X
      X O O O O X O X O O O X O O O O O O O O O O O O O O O O O O O O O O O O X O O O O X O O O O O O O X
    X X O O X O X O X O O X X O O O X O O O O O O O O O O O X O O O O O O O O X O O O X X X O X O X O X X
  X X X O O X O X O X O O X X O O X X O O X O O O O X O O O X O X O X O O O X X O O O X X X O X O X O X X X
  X X X O X X O X O X X O X X X O X X O O X O O X O X O X X X O X O X O X O X X O O X X X X X X O X O X X X
  X X X O X X O X O X X O X X X O X X O X X O O X O X O X X X O X O X O X O X X O O X X X X X X X X X X X X X
X X X X O X X X X X X X X X X X X X X X X X O X X O X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X
X X X X X X X X X X X X X X X X X X X X X X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X

</pre>

Then the sun comes out from behind the clouds, it partly evaporates the water from the top of the pits.

The sun can evaporate up to `2` squares.

**For example**: some amount of water is evaporated. As a result, finally only `99` squares of water are collected.

<pre>

      X             X       X                                                           X
      X         X   X       X                                                 X         X               X
    X X O O X O X O X O O X X O O O X O O O O O O O O O O O X O O O O O O O O X O O O X X X   X   X   X X
  X X X O O X O X O X O O X X O O X X O O X O O O O X O O O X O X O X O O O X X O O O X X X O X O X O X X X
  X X X O X X O X O X X O X X X O X X O O X O O X O X O X X X O X O X O X O X X O O X X X X X X O X O X X X
  X X X O X X O X O X X O X X X O X X O X X O O X O X O X X X O X O X O X O X X O O X X X X X X X X X X X X X
X X X X O X X X X X X X X X X X X X X X X X O X X O X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X
X X X X X X X X X X X X X X X X X X X X X X O X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X X

</pre>

## Solution

### Compile Code

Navigate to the project folder's root, i.e., `/landscape` (where the `pom.xml` file is located) -- then run:

```
$ mvn clean install
```
Find the `landscape-1.0.0-SNAPSHOT.jar` that will be produced by maven as a result.

Then run the jar file while passing numbers as arguments to represent the landscape you want to analyze for the Water Towers scenario.

### Example

```
java -jar landscape-1.0.0-SNAPSHOT.jar 5 9 0 6 4 7 4 8 5 3 7 5 8 3 6 7 5 3 9 7 3 4 3
```

