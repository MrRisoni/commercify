<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableShippingAddress extends AbstractMigration
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
        $shipAddress = $this->table('shipping_address', ['signed' => false]);
        $shipAddress->addColumn('user_id', 'biginteger', ['signed' => false])
            ->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('city', 'string', ['limit' => 80])
            ->addColumn('region', 'string', ['limit' => 80])
            ->addColumn('full_name', 'string', ['limit' => 80])
            ->addColumn('address', 'string', ['limit' => 80])
            ->addColumn('street_no', 'string', ['limit' => 20])
            ->addColumn('post_code', 'string', ['limit' => 20])
            ->addForeignKey('user_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
