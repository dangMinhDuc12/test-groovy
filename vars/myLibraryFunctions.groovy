def functionOne(param) {
  echo "Hello ${param}"
}

def functionTwo() {
  echo "Hello from function two"
}

def testGetBuildCacheSize() {
   // Get Docker build cache size
                    def buildCacheSize = sh(script: """
                        docker system df --format '{{json .}}' | \
                        jq -r '.[] | select(.Type == "Build Cache") | .Size'
                    """, returnStdout: true).trim()
                    
                    // Convert size to bytes for comparison 
                    def buildCacheSizeBytes = sh(script: """
                        numfmt --from=iec --to=bytes '$buildCacheSize'
                    """, returnStdout: true).trim().toInteger()

                    echo ${buildCacheSizeBytes}
}