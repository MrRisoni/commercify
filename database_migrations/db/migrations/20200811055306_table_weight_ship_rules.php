<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableWeightShipRules extends AbstractMigration
{

    public function change(): void
    {

        $weightRules = $this->table('shop_weight_ship_rules', ['signed' => false]);
        $weightRules->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('shipping_class_id', 'biginteger', ['signed' => false])
        ->addColumn('title', 'string', ['limit' => 52])
        ->addColumn('taxable', 'boolean')
        ->addColumn('created_at', 'datetime')
        ->addColumn('updated_at', 'datetime', ['null' => true])
        ->addColumn('less_than_kg', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('less_equal', 'boolean')
        ->addColumn('over_than_kg', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('over_equal', 'boolean')
        ->addColumn('base_cost', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('charge', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('over_total_weight', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('for_each_kg', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('active', 'boolean')
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('shipping_class_id', 'shop_courier_classes', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();





    }
}
