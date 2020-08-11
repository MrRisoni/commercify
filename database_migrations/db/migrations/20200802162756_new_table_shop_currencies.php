<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopCurrencies extends AbstractMigration
{

    public function change(): void
    {
        $shopCurrencies = $this->table('shop_currencies', ['signed' => false]);
        $shopCurrencies->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('currency_id', 'biginteger', ['signed' => false])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('currency_id', 'currencies', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
