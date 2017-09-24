/**
 * The tests for the CodingGame puzzles observe an output for several different inputs.
 * The input is provided on the program's standard input, the output is provided on the standard out.
 * Therefore unit tests must operate on the std in/out, rather than on return values of object methods.
 *
 * To solve this easily, an AbstractSolutionTest is provided for executing unit tests with given parameters.
 * The child Unit test classes should just provide a test subject class and a given set of parameters.
 * The parameters are simple pairs of provided values with an expected value.
 *
 * The following example shows an input string consisting of three lines and an expected value of "returned third line"
 *
 * {"first line\nsecond line\nthird line", "returned third line"},
 *
 */
package com.gembaboo.training.codingame;