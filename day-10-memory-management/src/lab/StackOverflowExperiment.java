package lab;

/**
 * Experiment 1: StackOverflowError Depth Measurement
 * Demonstrates thread stack frame limits and how the -Xss JVM flag
 * directly impacts recursion depth before stack memory exhaustion.
 */
public class StackOverflowExperiment {
    private static int depth = 0;

    public static int measureDepth() {
        depth = 0;
        try {
            recursiveCall(1);
        } catch (StackOverflowError e) {
            return depth;
        }
        return depth;
    }

    private static void recursiveCall(int currentDepth) {
        depth = currentDepth;
        // Recursive call without base condition consumes new stack frame
        // storing frame pointer, return address, and local variable currentDepth
        recursiveCall(currentDepth + 1);
    }

    public static void runDemo() {
        System.out.println("============================================================");
        System.out.println(" [EXP 1] STACKOVERFLOWERROR DEPTH (THREAD STACK -Xss)");
        System.out.println("============================================================");
        int maxDepth = measureDepth();
        System.out.println("Measured Call Stack Depth on Default JVM -Xss: " + maxDepth + " frames.");
        System.out.println("Key Insight: Each method invocation pushes a new stack frame containing");
        System.out.println("local variables, operand stack, and return address. Deeper call stacks");
        System.out.println("or unoptimized recursions exhaust thread stack space (-Xss).");
    }
}
