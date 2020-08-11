<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableShippingClasses extends AbstractMigration
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
        $courierClasses = $this->table('shop_courier_classes', ['signed' => false]);
        $courierClasses->addColumn('shop_courier_id', 'biginteger', ['signed' => false])
        ->addColumn('title', 'string', ['limit' => 52])
        ->addColumn('active', 'boolean')
        ->addForeignKey('shop_courier_id', 'shop_couriers', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
