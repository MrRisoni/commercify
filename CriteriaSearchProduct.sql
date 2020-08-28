select
            products0_.id as id1_21_,
            products0_.title as title19_21_
      
        from
            products products0_
        where
            products0_.price>=10.0
            and products0_.price<=5000.0
            and (
                products0_.id in (
                    select
                        products1_.id
                    from
                        products products1_
                    inner join
                        product_attributes_values productatt2_
                            on products1_.id=productatt2_.product_id
                    inner join
                        product_category_attributes productcat3_
                            on productatt2_.attribute_id=productcat3_.id
                    where
                        (
                            productatt2_.value_numeric between 1 and 1300
                        )
                        and productcat3_.code='Pages'
                )
            )
        order by
            products0_.price asc

            0.0441 seconds
            