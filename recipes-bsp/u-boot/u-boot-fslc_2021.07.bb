require recipes-bsp/u-boot/u-boot.inc
require u-boot-fslc-common_${PV}.inc

DESCRIPTION = "U-Boot based on mainline U-Boot used by FSL Community BSP in \
order to provide support for some backported features and fixes, or because it \
was submitted for revision and it takes some time to become part of a stable \
version, or because it is not applicable for upstreaming."

inherit ${@oe.utils.ifelse(d.getVar('UBOOT_PROVIDES_BOOT_CONTAINER') == '1', 'imx-boot-container', '')}

DEPENDS += "bc-native dtc-native python3-setuptools-native lzop-native"

# Location known to imx-boot component, where U-Boot artifacts
# should be additionally deployed.
# See below note above do_deploy:append:mx8m for the purpose of
# this delopyment location
BOOT_TOOLS = "imx-boot-tools"

PROVIDES += "u-boot"

B = "${WORKDIR}/build"

# FIXME: Allow linking of 'tools' binaries with native libraries
#        used for generating the boot logo and other tools used
#        during the build process.
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CPPFLAGS}" \
                 HOSTLDFLAGS="${BUILD_LDFLAGS}" \
                 HOSTSTRIP=true'

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mxs|mx5|mx6|mx7|vf|use-mainline-bsp)"
