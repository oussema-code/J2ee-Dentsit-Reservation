#!/bin/bash

# J2EE Project Deployment Script for WildFly

WILDFLY_HOME=/opt/wildfly
WAR_FILE=target/j2eeproject-1.0-SNAPSHOT.war
DEPLOYMENT_DIR=$WILDFLY_HOME/standalone/deployments

echo "Deploying j2eeproject to WildFly..."

# Check if WAR file exists
if [ ! -f "$WAR_FILE" ]; then
    echo "Error: WAR file not found. Please run 'mvn clean package' first."
    exit 1
fi

# Copy WAR to WildFly deployments directory
sudo cp $WAR_FILE $DEPLOYMENT_DIR/

echo "Deployment complete!"
echo "Application should be available at: http://localhost:8080/j2eeproject-1.0-SNAPSHOT/"
