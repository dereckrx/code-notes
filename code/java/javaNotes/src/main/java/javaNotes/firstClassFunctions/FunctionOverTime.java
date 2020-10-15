/*
source: https://github.com/samykhelifa/jessitron/tree/master/src/com/jessitron/fp4j/functionsAsValues
 */

package javaNotes.firstClassFunctions;

@FunctionalInterface
public interface FunctionOverTime {

    double valueAt(int time);
}
