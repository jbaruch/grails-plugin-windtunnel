package org._10ne.grails.windtunnel.executor

import org._10ne.grails.windtunnel.model.FlightPlan

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author Noam Y. Tenne.
 */
class GrailsPilot {
    private FlightPlan plan
    private Path grailsInstallation
    private Path grailsExec

    GrailsPilot(FlightPlan plan) {
        this.plan = plan
        grailsInstallation = Paths.get(System.getProperty('user.home'), '.gvm', 'grails', plan.grailsVersion)
        if (Files.notExists(grailsInstallation)) {
            throw new Exception("Unable to find Grails installation at: ${grailsInstallation}")
        }
        if (!Files.isReadable(grailsInstallation)) {
            throw new Exception("Unable to access Grails installation at: ${grailsInstallation}")
        }
        grailsExec = grailsInstallation.resolve('bin').resolve('grails')
        if (!Files.isExecutable(grailsExec)) {
            throw new Exception("Unable to find Grails executable at: ${grailsExec}")
        }
    }

    Path createApp() {
        //grails non interactive mode
        //make sure that we are running from the correct place
        def commandOutput = new StringBuffer()
        def commandError = new StringBuffer()
        Process createGrailsWindtunnelApp = "${plan.testDirectory} grails create-app windtunnel-app".execute()
        createGrailsWindtunnelApp.consumeProcessOutput(commandOutput, commandError)
        createGrailsWindtunnelApp.consumeProcessErrorStream(commandError)
        println 'sout: ' + commandOutput // => test text
        println 'commandError: ' + commandError



    }
}
