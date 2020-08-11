<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableOrders extends AbstractMigration
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
        $orders= $this->table('orders', ['signed' => false]);
        $orders->addColumn('user_id', 'biginteger', ['signed' => false])
        ->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('status_id', 'biginteger', ['signed' => false])
        ->addColumn('pay_method_id', 'biginteger', ['signed' => false])
        ->addColumn('ship_class_id', 'biginteger', ['signed' => false])
        ->addColumn('currency', 'string', ['limit' => 3])
        ->addColumn('total', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('net', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('tax', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('shipping', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('success', 'boolean')
        ->addColumn('void', 'boolean')
        ->addColumn('refund', 'boolean')
        ->addColumn('created_at', 'datetime')
        ->addColumn('updated_at', 'datetime', ['null' => true])
        ->addForeignKey('user_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('status_id', 'order_status', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('pay_method_id', 'payment_methods', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('ship_class_id', 'shop_courier_classes', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();



        ->create();
    }
}
