<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableBanks extends AbstractMigration
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
        $shopBanks = $this->table('shop_banks', ['signed' => false]);
        $shopBanks->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('bank', 'string', ['limit' => 52])
            ->addColumn('account_no', 'string', ['limit' => 52])
            ->addColumn('iban', 'string', ['limit' => 52])
            ->addColumn('swift_code', 'string', ['limit' => 10])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
