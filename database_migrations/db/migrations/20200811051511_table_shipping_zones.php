<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableShippingZones extends AbstractMigration
{

    public function change(): void
    {
        $shopShipZones = $this->table('shop_ship_zones', ['signed' => false]);
        $shopShipZones->addColumn('shop_id', 'biginteger', ['signed' => false]);
        $shopShipZones->addColumn('title', 'string', ['limit' => 55])
        ->addColumn('ship_cost', 'decimal', ['precision' => 10,'scale'=>2])
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
