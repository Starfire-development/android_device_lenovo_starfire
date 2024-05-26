PRODUCT_SUPPORTS_BOOT_SIGNER := true
PRODUCT_SUPPORTS_VERITY := true
PRODUCT_SUPPORTS_VERITY_FEC := false

# The dev key is used to sign boot and recovery images, and the verity
# metadata table. Actual product deliverables will be re-signed by hand.
# We expect this file to exist with the suffixes ".x509.pem" and ".pk8".
PRODUCT_VERITY_SIGNING_KEY := build/target/product/security/verity

PRODUCT_PACKAGES += \
    verity_key
