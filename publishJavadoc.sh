gradle releaseJavadoc
git clone --branch=gh-pages --single-branch git@github.com:victorhaggqvist/AboutIt.git ghpages
pushd ghpages
rm -rf *
popd
cp -r aboutit/build/docs/javadoc/* ghpages
pushd ghpages
git add .
git commit -m "Update JavaDoc"
git push --force
popd
rm -rf ghpages