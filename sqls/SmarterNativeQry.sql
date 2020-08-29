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
                       product_id
                       FROM   product_attributes_values
                       WHERE attribute_id  =13
                       AND value_numeric >=1
                       AND value_numeric <=1500
                )
            )
        order by
            products0_.price asc


            0.0182