#!/bin/bash
# Usage: ./run_adlib.sh [SDK.apk|SDK.jar] [init.json] [sensitivity] [alias]
#   sensitivity: ps | ci | cs1 cs2 cs3 | os1 os2 os3
# Examples:
#   ./run_adlib.sh
#   ./run_adlib.sh /path/to/other.apk
#   ./run_adlib.sh /path/to/app.apk /path/to/init.json ci alias

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DEFAULT_APK="/Users/mathiasgredal/git/test/tools/DeepExploitor/app/samsung.apk"
DEFAULT_INIT="$PROJECT_DIR/inital"

SDK="${1:-$DEFAULT_APK}"
INIT_JSON="${2:-$DEFAULT_INIT}"
SENS="${3:-ci}"

CMD=(java -Xmx8G -jar "$PROJECT_DIR/Adlib.jar" -p "$PROJECT_DIR/wala.properties" -sdk "$SDK" -init "$INIT_JSON" -s "$SENS")

if [ "${4:-}" = "alias" ]; then
  CMD+=(-alias)
fi

exec "${CMD[@]}"
