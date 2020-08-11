<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopCountries extends AbstractMigration
{

    public function change(): void
    {
        $shopCountries = $this->table('shop_countries', ['signed' => false]);
        $shopCountries->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
