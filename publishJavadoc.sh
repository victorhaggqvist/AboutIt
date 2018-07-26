#!/bin/bash

set -ex

REPO="git@github.com:victorhaggqvist/AboutIt.git"
GROUP_ID="com.snilius.aboutit"
ARTIFACT_ID="aboutit"

DIR=temp-clone

# Delete any existing temporary website clone
rm -rf $DIR

# Clone the current repo into temp folder
git clone --branch=gh-pages --single-branch $REPO $DIR

# Move working directory into temp folder
pushd $DIR

# Delete everything
rm -rf *

# Download the latest javadoc
curl -L "https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=$GROUP_ID&a=$ARTIFACT_ID&v=LATEST&c=javadoc" > javadoc.zip
unzip javadoc.zip
rm javadoc.zip

# Stage all files in git and create a commit
git add .
git add -u
git commit -m "Update JavaDoc at $(date)"

# Push the new files up to GitHub
git push origin gh-pages

# Delete our temp folder
popd
rm -rf $DIR