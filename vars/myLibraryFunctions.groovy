def functionOne(param) {
  echo "Hello ${param}"
}

def functionTwo() {
  echo "Hello from function two"
}

def testGetBuildCacheSize() {
  // Get the raw size output from the Docker command
    def rawSize = sh(script: 'docker system df --format "{{.Size}}" | awk \'NR==4{print $1}\'', returnStdout: true).trim()
    echo "Raw build cache size: $rawSize"

    // Function to convert size to GB
    def convertToGB = { size ->
        def unit = size[-2..-1] // Get the last two characters (MB, GB, etc.)
        def value = size[0..-3].toFloat() // Get the numerical value
        switch(unit) {
            case "MB":
                return value / 1024 // Convert MB to GB
            case "GB":
                return value // Already in GB
            default:
                return 0 // Unsupported unit
        }
    }

     // Convert the raw size to GB
    def cacheSizeInGB = convertToGB(rawSize)
    echo "Build cache size in GB: $cacheSizeInGB GB"

    // Define your cache size limit in GB
    def cacheLimitGB = 1.0 // example value in GB
    
    if (cacheSizeInGB > cacheLimitGB) {
        echo "Cache size exceeds limit. Pruning cache..."
    } else {
        echo "Cache size is within limit. No action needed."
    }
}