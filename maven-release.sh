#!/bin/bash
# Deploy maven artifact in current directory to Maven central repository using maven-release-plugin goals
stty -echo
printf "Enter GPG passphrase: "
read GPG_PASSPHRASE
stty echo
printf "\n"

# this will ask for release version confirmation
# if automated version bump is needed use "mvn -B ..." (batch)
mvn -e release:prepare release:perform -Darguments=-Dgpg.passphrase=$GPG_PASSPHRASE | tee release.log

