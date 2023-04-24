#!/bin/bash

export PATH=/Library/Java/JavaVirtualMachines/graalvm-ee-java19-22.3.1/Contents/Home/bin:$PATH
mvn clean deploy -P release -DadditionalJOption=-Xdoclint:none