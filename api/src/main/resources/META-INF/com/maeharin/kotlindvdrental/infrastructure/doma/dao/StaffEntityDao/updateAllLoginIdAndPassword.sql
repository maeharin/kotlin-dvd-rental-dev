update
    staff
set
    login_id = /* loginIdPrefix */'xxx' || cast(staff_id as varchar),
    password_digest = /* passwordDigest */'xxx'
