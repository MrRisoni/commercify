<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AddBillShipAddress extends AbstractMigration
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
        $orderTable = $this->table('orders');
        $orderTable->addColumn('shipping_address_id', 'biginteger', ['signed' => false,'after' => 'user_id'])->update();
        $orderTable->addColumn('billing_address_id', 'biginteger', ['signed' => false,'after' => 'shipping_address_id'])->update();

        $orderTable->addForeignKey('shipping_address_id', 'shipping_address', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('billing_address_id', 'billing_address', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])->update();
   
        
    }
}
