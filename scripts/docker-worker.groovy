import com.nirima.jenkins.plugins.docker.DockerCloud
import com.nirima.jenkins.plugins.docker.DockerTemplate
import com.nirima.jenkins.plugins.docker.DockerTemplateBase
import com.nirima.jenkins.plugins.docker.launcher.AttachedDockerComputerLauncher
import io.jenkins.docker.connector.DockerComputerAttachConnector
import jenkins.model.Jenkins

// parameters

def dockerCloudParameters = [
name:             'Adas-Project-Server-18',
serverUrl:        'tcp://10.10.1.2:4243',
credentialsId:    '',
version:          '1.26',
connectTimeout:   5,
readTimeout:      15,
dockerHostname:   '',
containerCapStr:  '5' 
]

def DockerTemplateParameters = [
labelString:    'docker-adas-linux',
instanceCapStr: '1',
remoteFs:       '/var/lib/jenkins'
]

def dockerTemplateBaseParameters = [
image:              'adas-build:linux',
pullCredentialsId:  '',
dnsString:          '',
network:            '',
dockerCommand:      '',
volumesString:      '',
volumesFromString:  '',
environmentsString: '',
hostname:           '',
user:               '',
extraGroupsString:  '',
memoryLimit:        null,
memorySwap:         null,
cpuShares:          null,
sharedMemorySize:   null,
bindPorts:          '',
bindAllPorts:       false,
privileged:         true,
tty:                false,
macAddress:         '',
extraHostsString:  ''
]

DockerTemplateBase dockerTemplateBase = new DockerTemplateBase(
dockerTemplateBaseParameters.image,
dockerTemplateBaseParameters.pullCredentialsId,
dockerTemplateBaseParameters.dnsString,
dockerTemplateBaseParameters.network,
dockerTemplateBaseParameters.dockerCommand,
dockerTemplateBaseParameters.volumesString,
dockerTemplateBaseParameters.volumesFromString,
dockerTemplateBaseParameters.environmentsString,
dockerTemplateBaseParameters.hostname,
dockerTemplateBaseParameters.user,
dockerTemplateBaseParameters.extraGroupsString,
dockerTemplateBaseParameters.memoryLimit,
dockerTemplateBaseParameters.memorySwap,
dockerTemplateBaseParameters.cpuShares,
dockerTemplateBaseParameters.sharedMemorySize,
dockerTemplateBaseParameters.bindPorts,
dockerTemplateBaseParameters.bindAllPorts,
dockerTemplateBaseParameters.privileged,
dockerTemplateBaseParameters.tty,
dockerTemplateBaseParameters.macAddress,
dockerTemplateBaseParameters.extraHostsString
)

DockerTemplate dockerTemplate = new DockerTemplate(
dockerTemplateBase,
new DockerComputerAttachConnector(),
DockerTemplateParameters.labelString,
DockerTemplateParameters.remoteFs,
DockerTemplateParameters.instanceCapStr
)

DockerCloud dockerCloud = new DockerCloud(
dockerCloudParameters.name,
[dockerTemplate],
dockerCloudParameters.serverUrl,
dockerCloudParameters.containerCapStr,
dockerCloudParameters.connectTimeout,
dockerCloudParameters.readTimeout,
dockerCloudParameters.credentialsId,
dockerCloudParameters.version,
dockerCloudParameters.dockerHostname
)

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// add cloud configuration to Jenkins
jenkins.clouds.add(dockerCloud)

// save current Jenkins state to disk
jenkins.save()
