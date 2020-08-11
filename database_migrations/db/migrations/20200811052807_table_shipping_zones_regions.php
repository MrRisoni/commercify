<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableShippingZonesRegions extends AbstractMigration
{

    
    public function change(): void
    {
        $shipZonesRegions = $this->table('shipping_zones_regions', ['signed' => false]);
        $shipZonesRegions->addColumn('zone_id', 'biginteger', ['signed' => false])
        ->addColumn('region_id', 'biginteger', ['signed' => false])
        ->addForeignKey('region_id', 'globe_regions', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('zone_id', 'shop_ship_zones', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
