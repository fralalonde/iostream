#!/bin/bash
# Deploy maven artifact in current directory to Maven central repository using maven-release-plugin goals
stty -echo
printf "Enter GPG passphrase:"
read GPG_PASSPHRASE
stty echo
printf "\n"

mvn release:prepare release:perform -B -e -Darguments=-Dgpg.passphrase=$GPG_PASSPHRASE | tee release.log
