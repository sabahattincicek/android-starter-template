# Project-specific R8 rules for release builds.
#
# Hilt, Room, Retrofit, OkHttp, kotlinx.serialization, and Navigation 3 ship their own consumer
# rules, so nothing is needed for them here. Add rules only for code that R8 cannot see being used,
# such as classes accessed through reflection.

# Keep line numbers in crash reports while hiding the original source file names.
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
