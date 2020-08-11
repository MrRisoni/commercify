<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class OrderItems extends AbstractMigration
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
        $orderItems = $this->table('order_items', ['signed' => false]);
        $orderItems->addColumn('order_id', 'biginteger', ['signed' => false])
        ->addColumn('product_id', 'biginteger', ['signed' => false])
        ->addColumn('status_id', 'biginteger', ['signed' => false])
        ->addColumn('quantity', 'integer', ['signed' => false])
        ->addColumn('tracking_no', 'string', ['limit' => 52])
        ->addColumn('net_price', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('taxes', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('gift_cost', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('success', 'boolean')
        ->addColumn('void', 'boolean')
        ->addColumn('refund', 'boolean')
        ->addForeignKey('order_id', 'orders', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('product_id', 'products', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('status_id', 'order_status', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();

    }
}
