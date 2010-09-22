#! /bin/bash

REPO=$HOME/.m2/repository
SCALA_VERSION=2.8.0
VSCALADOC2_VERSION=0.1

PRJ_DIR=$(dirname $0)
CFG_FILE=${PRJ_DIR}/target/vscaladoc2_cfg.json

rm -Rf ${PRJ_DIR}/target/apidoc/scaladocs
mkdir -p ${PRJ_DIR}/target

cat >${CFG_FILE} <<EOF
{
  "artifactId" : "vscaladoc_demoprj",
  "version" : "0.1-SNAPSHOT",
  "apidocdir" : "${PRJ_DIR}/target/apidoc",
  "dependencies" : [
    ["${REPO}/org/scala-lang/scala-library/${SCALA_VERSION}/scala-library-${SCALA_VERSION}.jar", "scala-library", "${SCALA_VERSION}"]
  ],
  "sources" : [
    ["${PRJ_DIR}/src/main/scala"]
  ]
}                
EOF

CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/scala-lang/scala-library/${SCALA_VERSION}/scala-library-${SCALA_VERSION}.jar
CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/scala-lang/scala-compiler/${SCALA_VERSION}/scala-compiler-${SCALA_VERSION}.jar
CLASSPATH_DEPS=${CLASSPATH_DEPS}:${REPO}/org/codehaus/jackson/jackson-core-asl/1.6.0/jackson-core-asl-1.6.0.jar
#MAIN_JAR=${REPO}/org/scala-tools/vscaladoc2/vscaladoc2_genjson/${VSCALADOC2_VERSION}/vscaladoc2_genjson-${VSCALADOC2_VERSION}.jar
MAIN_JAR=${PRJ_DIR}/../vscaladoc2_genjson/target/classes
echo $MAIN_JAR

MAIN_CLASS=net_alchim31_vscaladoc2_genjson.Main

java -Xmx1024m -cp ${CLASSPATH_DEPS}:${MAIN_JAR} ${MAIN_CLASS} ${CFG_FILE}

#or java -Xmx1024m -Xbootclasspath/a:${CLASSPATH_DEPS} -jar ${MAIN_JAR} ${CFG_FILE}
#or mop execjar org.scala-tools.vscaladoc2:vscaladoc2_genjson:${VSCALADOC2_VERSION} ${CFG_FILE}
