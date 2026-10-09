public class PlatformInfo {
    public static void main(String[] args) {
        System.out.println("Java Environment Info");
        
        System.out.println("Version: " + System.getProperty("java.version"));
        System.out.println("Vendor: " + System.getProperty("java.vendor"));
        System.out.println("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Processors: " + runtime.availableProcessors());
        
        long maxMB = runtime.maxMemory() / (1024 * 1024);
        long freeMB = runtime.freeMemory() / (1024 * 1024);
        long totalMB = runtime.totalMemory() / (1024 * 1024);
        
        System.out.println("Max Memory: " + maxMB + " MB");
        System.out.println("Total Allocated: " + totalMB + " MB");
        System.out.println("Free Allocated: " + freeMB + " MB");
    }
}
