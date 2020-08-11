<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableShippingClasses extends AbstractMigration
{

    public function change(): void
    {
        $courierClasses = $this->table('shop_courier_classes', ['signed' => false]);
        $courierClasses->addColumn('shop_courier_id', 'biginteger', ['signed' => false])
        ->addColumn('title', 'string', ['limit' => 52])
        ->addColumn('active', 'boolean')
        ->addForeignKey('shop_courier_id', 'shop_couriers', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
