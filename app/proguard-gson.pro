# Proguard rules for GSON library
# Prevents obfuscation of GSON classes and serializable classes

# Keep GSON classes
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

# Keep classes with @SerializedName
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Keep classes with @Expose
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.Expose <fields>;
}

# Keep annotations
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

# Preserve generic signature of TypeToken
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken { *; }

# Keep custom serializers/deserializers
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Keep safe classes
-keep class com.google.gson.** { <init>(...); }

# Keep enum constants
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
