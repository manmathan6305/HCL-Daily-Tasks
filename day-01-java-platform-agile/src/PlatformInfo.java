public class PlatformInfo {
    public static void main(String[] args) {
        System.out.println("=== Java Platform & Environment Info ===");
        
        // Java Version Info
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Vendor: " + System.getProperty("java.vendor"));
        
        // Operating System Info
        System.out.println("Operating System: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        
        // Hardware/Runtime Info
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Available Processors: " + runtime.availableProcessors());
        
        // Memory Info (Converted from bytes to Megabytes)
        long maxMemoryMB = runtime.maxMemory() / (1024 * 1024);
        long freeMemoryMB = runtime.freeMemory() / (1024 * 1024);
        long totalMemoryMB = runtime.totalMemory() / (1024 * 1024);
        
        System.out.println("Max Heap Memory: " + maxMemoryMB + " MB");
        System.out.println("Total Allocated Memory: " + totalMemoryMB + " MB");
        System.out.println("Free Memory in Allocation: " + freeMemoryMB + " MB");
        
        System.out.println("========================================");
    }
}
