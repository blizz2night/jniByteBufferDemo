#include <jni.h>
#include <string>
#include <android/log.h>
extern "C" JNIEXPORT jstring JNICALL
Java_com_github_blizz2night_bytebufferdemo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_github_blizz2night_bytebufferdemo_MainActivity_byteFromJNI(JNIEnv *env, jobject thiz, jobject byte_buffer) {
    // TODO: implement byteFromJNI()
    uint8_t* addr = static_cast<uint8_t*>(env->GetDirectBufferAddress(byte_buffer));
    jlong length = env->GetDirectBufferCapacity(byte_buffer);
    for (int i = 0; i < length; i++) {
        __android_log_print(ANDROID_LOG_VERBOSE,"MainActivity"," native-lib RED little-endian byteFromJNI=%02X",*(addr+i));
    }
}