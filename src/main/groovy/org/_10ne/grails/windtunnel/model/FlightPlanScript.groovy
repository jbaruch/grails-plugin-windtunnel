package org._10ne.grails.windtunnel.model

/**
 * @author Noam Y. Tenne.
 */
abstract class FlightPlanScript extends Script {

    def usingGrails(String grailsVersion) {
        this.binding.flight.grailsVersion = grailsVersion
    }

    def testPlugin(Map<Object, Object> pluginSource) {
        this.binding.flight.pluginData = new PluginSource(pluginSource)
    }

    def at(String testDirectoryAbsolutePath) {
        this.binding.flight.testDirectory = testDirectoryAbsolutePath
    }
}
