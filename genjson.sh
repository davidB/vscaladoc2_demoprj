#! /bin/bash

REPO=$HOME/.m2/repository
SCALA_VERSION=2.8.0
VSCALADOC2_VERSION=0.1


PRJ_DIR=$(dirname $0)
PRJ_ARTIFACTID=$1
if [ "x$PRJ_ARTIFACID" == "x" ] ; then
  PRJ_ARTIFACTID=$(basename $(pwd))
fi
PRJ_VERSION=$2
if [ "x$PRJ_VERSION" == "x" ] ; then
  PRJ_VERSION=0.1-SNAPSHOT
fi

PRJ_CFG_FILE=${PRJ_DIR}/target/vscaladoc2_cfg.json
cat >${PRJ_CFG_FILE} <<EOF
{
  "artifactId" : "${PRJ_ARTIFACTID}",
  "version" : "${PRJ_VERSION}",
  "dependencies" : [
    ["${REPO}/org/scala-lang/scala-library/${SCALA_VERSION}/scala-library-${SCALA_VERSION}.jar", "scala-library/${SCALA_VERSION}"]
  ],
  "sources" : [
    ["${PRJ_DIR}/src/main/scala"]
  ]
}
EOF

SCALALIB_CFG_FILE=${PRJ_DIR}/target/scala-library_cfg.json
cat >${SCALALIB_CFG_FILE} <<EOF
{
  "artifactId" : "scala-library",
  "groupId" : "org.scala-lang",
  "version" : "2.8.0",
  "logo" : "<a href='http://www.scala-lang.org/'><img src='http://lampsvn.epfl.ch/images/Scala_Logo2008.png'/></a>",
  "dependencies" : [
    ["${REPO}/org/scala-lang/scala-library/${SCALA_VERSION}/scala-library-${SCALA_VERSION}.jar", "scala-library/${SCALA_VERSION}"]
  ],
  "sources" : [
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/library", [], ["**/*.scala"]],
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/continuations/library"],
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/swing", ["scala/swing/test/**"]],
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/fjbg"],
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/forkjoin"],
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/actors", [], ["**/*.scala"]]
  ]
}
EOF

SCALAC_CFG_FILE=${PRJ_DIR}/target/scala-compiler_cfg.json
cat >${SCALAC_CFG_FILE} <<EOF
{
  "artifactId" : "scala-compiler",
  "groupId" : "org.scala-lang",
  "version" : "2.8.0",
  "dependencies" : [
    ["${REPO}/org/scala-lang/scala-library/${SCALA_VERSION}/scala-library-${SCALA_VERSION}.jar", "scala-library/${SCALA_VERSION}"]
  ],
  "sources" : [
    ["${HOME}/tmp/scala-2.8.0.final-sources/src/compiler"]
  ]
}
EOF

CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/scala-lang/scala-library/${SCALA_VERSION}/scala-library-${SCALA_VERSION}.jar
CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/scala-lang/scala-compiler/${SCALA_VERSION}/scala-compiler-${SCALA_VERSION}.jar
CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/codehaus/jackson/jackson-core-asl/1.6.0/jackson-core-asl-1.6.0.jar
CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/jsoup/jsoup/1.3.3/jsoup-1.3.3.jar
#MAIN_JAR=${REPO}/org/scala-tools/vscaladoc2/vscaladoc2_genjson/${VSCALADOC2_VERSION}/vscaladoc2_genjson-${VSCALADOC2_VERSION}.jar
MAIN_JAR=${PRJ_DIR}/../vscaladoc2_genjson/target/classes
echo $MAIN_JAR

CFG_FILE=${SCALALIB_CFG_FILE}
#CFG_FILE=${SCALAC_CFG_FILE}
#CFG_FILE=${PRJ_CFG_FILE}

MAIN_CLASS=net_alchim31_vscaladoc2_genjson.Main
JAVA_OPTS="-Xms512M -Xmx2048M -Xss1M -XX:MaxPermSize=128M"
java ${JAVA_OPTS} -cp ${CLASSPATH_DEPS}:${MAIN_JAR} ${MAIN_CLASS} ${CFG_FILE}

#or java -Xmx1024m -Xbootclasspath/a:${CLASSPATH_DEPS} -jar ${MAIN_JAR} ${CFG_FILE}
#or mop execjar org.scala-tools.vscaladoc2:vscaladoc2_genjson:${VSCALADOC2_VERSION} ${CFG_FILE}
