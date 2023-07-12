#!/usr/bin/env bash

set -euo pipefail

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SYNC_DIR="$( cd "$SCRIPT_DIR" && pwd )"

REMOTE_MACHINE="$( grep "^remote_machine=" "$SYNC_DIR/.mainframer/config" | cut -d'=' -f2 )"

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SYNC_DIR="$( cd "$SCRIPT_DIR/../.." && pwd )"

if [ -z "$REMOTE_MACHINE" ]; then
	echo "Please pass host name as the first argument."
	exit 1
fi

function checkAndroidHomeVariable {
    if [[ -z "$ANDROID_HOME" ]]; then
        local androidHomeFromProject="$(sed -n '/<property name="android.sdk.path"/s/.*value="\(.*\)"[^\n]*/\1/p' ${PROJECT_DIR}/.idea/workspace.xml | sed 's/$USER_HOME$/~/g')"
        if [[ -z ${androidHomeFromProject} ]]; then
            echo "Please specify location of your android sdk in ANDROID_HOME"
            exit 1
        fi
        ANDROID_HOME=${androidHomeFromProject}

        #replace $USER_HOME$ and $PROJECT_DIR$
        ANDROID_HOME=${ANDROID_HOME/\$USER_HOME\$/"${HOME}"}
        ANDROID_HOME=${ANDROID_HOME/\$PROJECT_DIR\$/"${PROJECT_DIR}"}
    fi
}

function setupAndroidSdk {
    local ANDROID_SDK_VERSION="commandlinetools-linux-8512546_latest.zip"
    local ANDROID_SDK_DOWNLOAD_BASE_LINK="https://dl.google.com/android/repository/"
    local ANDROID_SDK_DOWNLOAD_LINK="${ANDROID_SDK_DOWNLOAD_BASE_LINK}${ANDROID_SDK_VERSION}"
    local ANDROID_SDK_FOLDER_NAME="android-sdk"

    echo 001
    if [[ true ]]; then
        echo "Downloading SDK from ${ANDROID_SDK_DOWNLOAD_LINK}"
        wget -q ${ANDROID_SDK_DOWNLOAD_LINK}
        if [[ ! -f ${ANDROID_SDK_VERSION} ]]; then
            echo "Failed to download SDK!"
            exit 1
        fi

        echo "Unzip SDK from ${ANDROID_SDK_VERSION} to ${ANDROID_SDK_FOLDER_NAME}"
        unzip -q "$ANDROID_SDK_VERSION" -d "$ANDROID_SDK_FOLDER_NAME"
        mv "$ANDROID_SDK_FOLDER_NAME/cmdline-tools" "$ANDROID_SDK_FOLDER_NAME/latest"
        mkdir "$ANDROID_SDK_FOLDER_NAME/cmdline-tools"
        mv "$ANDROID_SDK_FOLDER_NAME/latest" "$ANDROID_SDK_FOLDER_NAME/cmdline-tools/latest"

        yes | "$ANDROID_SDK_FOLDER_NAME/cmdline-tools/latest/bin/sdkmanager" "cmake;3.10.2.4988404"

        echo "Removing ${ANDROID_SDK_VERSION}"
        rm "$ANDROID_SDK_VERSION"
        sed -i "1i export ANDROID_HOME=~/$ANDROID_SDK_FOLDER_NAME" ~/.bashrc
    fi
    echo 002
}

function installJavaOnRemoteMachineIfNeed {
    ssh -t ${REMOTE_MACHINE} "java -version || sudo apt-get --assume-yes install openjdk-11-jdk"
    echo "Java installed"
}

function setupAndroidSdkOnRemoteMachine {
    ssh ${REMOTE_MACHINE} "$(typeset -f); setupAndroidSdk"
    echo "Sdk downloaded to remote machine"
}

function copyLicensesToRemoteMachine {
    scp -r ${ANDROID_HOME}/licenses ${REMOTE_MACHINE}:"\$ANDROID_HOME"
}

function setupGradleSettings {
    ssh ${REMOTE_MACHINE} "$(typeset -f); createGradleProperties"
    echo "Gradle settings set"
}

function createGradleProperties {
    mkdir -p .gradle
    rm -f .gradle/gradle.properties

    echo 'org.gradle.daemon=false' >> .gradle/gradle.properties
    echo 'kotlin.compiler.execution.strategy=in-process' >> .gradle/gradle.properties
}

checkAndroidHomeVariable
installJavaOnRemoteMachineIfNeed
setupAndroidSdkOnRemoteMachine
copyLicensesToRemoteMachine
setupGradleSettings