def functionOne(param) {
  echo "Hello ${param}"
}

def functionTwo() {
  echo "Hello from function two"
}

def testGetBuildCacheSize() {
  def buildCacheSizeOutput = sh(
                        script: """docker system df --format '{{json .}}' | jq -r '[.[] | select(.Type == "Build Cache") | .Size] | if length > 0 then .[0] else "0B" end'""",
                        returnStdout: true
                    ).trim()
}