#
# Copyright (C) 2017 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Boot animation
TARGET_SCREEN_HEIGHT := 1080
TARGET_SCREEN_WIDTH := 1920

$(call inherit-product, $(SRC_TARGET_DIR)/product/core_base.mk)

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/product_launched_with_o_mr1.mk)
$(call inherit-product, device/lenovo/starfire/verity.mk)

# Inherit some common LineageOS stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

# Inherit device configuration
$(call inherit-product, device/lenovo/starfire/device.mk)

# Device identifier. This must come after all inclusions
PRODUCT_NAME := lineage_starfire
PRODUCT_DEVICE := starfire
PRODUCT_BRAND := Lenovo
PRODUCT_MODEL := Lenovo StarView
PRODUCT_MANUFACTURER := LENOVO

PRODUCT_GMS_CLIENTID_BASE := android-lenovo

PRODUCT_BUILD_PROP_OVERRIDES += \
    PRIVATE_BUILD_DESC="starfire_row-user 7.0 NRD90N P2a42_S251_171107_ROW release-keys" \
    TARGET_DEVICE="starfire"

BUILD_FINGERPRINT := Lenovo/starfire_row/P2a42:7.0/NRD90N/P2a42_S251_171107_ROW:user/release-keys
