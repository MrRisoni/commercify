<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class CodWeightRules extends AbstractMigration
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
        $CashOnDeliveryWeightRules = $this->table('shop_weight_cod_rules', ['signed' => false]);
        $CashOnDeliveryWeightRules->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('shipping_class_id', 'biginteger', ['signed' => false])
        ->addColumn('title', 'string', ['limit' => 52])
        ->addColumn('taxable', 'boolean')
        ->addColumn('less_than_kg', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('less_equal', 'boolean')
        ->addColumn('over_than_kg', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('over_equal', 'boolean')
        ->addColumn('base_cost', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('charge', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('over_total_weight', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('for_each_kg', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('active', 'boolean')
        ->addColumn('created_at', 'datetime')
        ->addColumn('updated_at', 'datetime', ['null' => true])
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('shipping_class_id', 'shop_courier_classes', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();

    }
}
