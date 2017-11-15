update
    customer
set
    login_id = /* loginIdPrefix */'xxx' || cast(customer_id as varchar),
    password_digest = /* passwordDigest */'xxx'
