<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopCouriers extends AbstractMigration
{

    public function change(): void
    {
        $shopCouriers = $this->table('shop_couriers', ['signed' => false]);
        $shopCouriers->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('courier_id', 'biginteger', ['signed' => false])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('courier_id', 'courriers', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
