!/bin/bash
# Deploy maven artifact in current directory to Maven central repository
# using maven-release-plugin goals
read -p "Deploy to Maven CENTRAL repository (yes/NO])? "
if ( [ "$REPLY" == "yes" ] ) then
  ssh-add ~/.ssh/fralalonde
  ssh-add -l
  mvn release:clean release:prepare release:perform -B -e | tee maven-central-deploy.log
  ssh-add -D
else
  echo 'Not deployed.'
fi