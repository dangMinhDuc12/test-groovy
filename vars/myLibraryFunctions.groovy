def functionOne(param) {
  echo "Hello ${param}"
}

def functionTwo() {
  echo "Hello from function two"
}

def testGetBuildCacheSize() {
 def buildCacheSizeOutput = sh(
                        script: "docker system df --format '{{json .}}' | jq '[.[] | select(.Type | contains(\"Build Cache\"))][0].Size' | sed 's/\"//g'", // Remove quotes
                        returnStdout: true
                    ).trim()

  echo ${buildCacheSizeOutput}
}