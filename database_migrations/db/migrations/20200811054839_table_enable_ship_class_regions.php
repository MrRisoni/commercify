<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableEnableShipClassRegions extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $shopShipClassRegions = $this->table('shop_shipping_classes_regions', ['signed' => false]);
        $shopShipClassRegions->addColumn('ship_class_id', 'biginteger', ['signed' => false])
        ->addColumn('region_id', 'biginteger', ['signed' => false])
        ->addColumn('active', 'boolean')
        ->addForeignKey('ship_class_id', 'shop_courier_classes', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('region_id', 'globe_regions', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
