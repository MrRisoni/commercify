<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableShippingZonesZipCodes extends AbstractMigration
{

    public function change(): void
    {
        $shipZonesZips = $this->table('shipping_zones_zip_codes', ['signed' => false]);
        $shipZonesZips->addColumn('zone_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('zip_code', 'string', ['limit' => 20])
            ->addForeignKey('zone_id', 'shop_ship_zones', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
