def functionOne(param) {
  echo "Hello ${param}"
}

def functionTwo() {
  echo "Hello from function two"
}

def testGetBuildCacheSize() {
  def dockerDfOutput = sh(script: 'docker system df --format "{{.BuildCache}}" | awk \'{print $1}\'', returnStdout: true).trim()
                    def cacheSizeGB = (dockerDfOutput.toFloat() / 1024 / 1024 / 1024).round(2)  // Convert to GB

                    echo "Current build cache size: ${cacheSizeGB} GB
}